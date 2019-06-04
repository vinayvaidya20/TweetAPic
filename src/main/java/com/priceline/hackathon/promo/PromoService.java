package com.priceline.hackathon.promo;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PromoService {

	public String getPromo() {

		String[] coupons = {"74Z9CQ8M36W","75B9HW94A5F","9SZ4CT9GC7","74R8Y24VE6A","6NG7SS6989","5JR7KM9GA3","74G9EX9LT5H","97J6E38QM9","73X8YR68L59","4EU5C76FH9","73H7ZP94J5V","72N9C854V3L","5FU26Z2LR4"};
		Random rand = new Random(System.currentTimeMillis());
		String coupon = coupons[rand.nextInt(coupons.length-1)];

		return coupon;
	}

}
