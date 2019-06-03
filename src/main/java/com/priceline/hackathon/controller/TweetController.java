package com.priceline.hackathon.controller;

import com.priceline.hackathon.Email.SendHTMLEmail;
import com.priceline.hackathon.Email.dto.Deal;
import com.priceline.hackathon.Email.dto.Email;
import com.priceline.hackathon.Email.dto.Trip;
import com.priceline.hackathon.Email.dto.TweetRequest;
import com.priceline.hackathon.Email.dto.User;
import com.priceline.hackathon.promo.PromoService;
import com.priceline.hackathon.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.TemplateEngine;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Controller
public class TweetController {

	private static String UPLOADED_FOLDER = "/tmp/";
	private List<User> emailList = new LinkedList<>();
	private ReadWriteLock rwLock = new ReentrantReadWriteLock();

	@Autowired
	private TweetService tweetService;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private SendHTMLEmail sendHTMLEmail;

	@Autowired
	private PromoService promoService;

	@GetMapping("/")
	public String index(RedirectAttributes redirectAttributes) {

		redirectAttributes.addFlashAttribute("message", "Had a great trip at Florida.. Thank you Priceline! :)");
		return "redirect:/tweet";
	}

	@GetMapping("/tweet")
	public String tweet() {
		return "tweet";
	}

	@GetMapping("/send")
	public ResponseEntity<?> send(@RequestParam(required = true) String firstName,
			@RequestParam(required = true) String lastName,
			@RequestParam(required = true) String email,
			@RequestParam(required = true) String city,
			@RequestParam(required = true) String date) {

		User user = new User();
		Trip trip = new Trip();

		trip.setCity(city);
		trip.setDate(date);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmailAddress(email);
		user.setTrip(trip);

		Email emailContent = new Email();
		emailContent.setUser(user);
		emailContent.setTemplete("email");
		emailContent.setSubject("Woaa! Priceline Deal: Share it on Twitter to get a deal on your next trip");

		Lock writeLock = rwLock.writeLock();
		writeLock.lock();
		try {
			emailList.add(user);
		} finally {
			writeLock.unlock();
		}

		sendHTMLEmail.sendEmail(emailContent);

		return ResponseEntity.ok("Sent");
	}

	@PostMapping("/upload") // //new annotation since 4.3
	public ResponseEntity<?> singleFileUpload(@RequestParam(required = false, value = "file") MultipartFile file,
			RedirectAttributes redirectAttributes, @RequestParam(required = false) String oauth_token, @RequestParam(required = false) String oauth_secret,
			@RequestParam(required = false) String userTweet) {

		if (null == file || file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return ResponseEntity.badRequest().body("redirect:uploadStatus");
		}

		try {

			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);

			TweetRequest tweetRequest = new TweetRequest(path, oauth_token, oauth_secret, userTweet);
			boolean tweetResponse = tweetService.uploadToTwitter(tweetRequest);

			User user = null;
			if (tweetResponse) {
				Lock readLock = rwLock.readLock();
				readLock.lock();
				try {
					user = emailList.get(0);
				} finally {
					readLock.unlock();
				}
				Deal deal = new Deal();
				deal.setDealCode(promoService.getPromo());
				deal.setPercentage("15%");
				user.setDeal(deal);

				sendCoupon(user);
			}

			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + file.getOriginalFilename() + "'");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.ok("pass");
	}

	private void sendCoupon(User user) {
		Email emailContent = new Email();
		emailContent.setUser(user);
		emailContent.setTemplete("coupon");
		emailContent.setSubject("Priceline Coupon for Sharing the deal ");
		sendHTMLEmail.sendEmail(emailContent);
	}

}
