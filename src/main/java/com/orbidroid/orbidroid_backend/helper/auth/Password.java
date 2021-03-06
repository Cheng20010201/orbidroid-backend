package com.orbidroid.orbidroid_backend.helper.auth;

import com.orbidroid.orbidroid_backend.helper.misc.Bijection;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password {

    public static String encrypt(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("String to encript cannot be null or zero length.");
        }
        StringBuffer hexString = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] hash = md.digest();
            for (int i = 0; i < hash.length; i++) {
                if ((0xff & hash[i]) < 0x10) {
                    hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
                } else {
                    hexString.append(Integer.toHexString(0xFF & hash[i]));
                }
            }
        } catch (NoSuchAlgorithmException e) { }
        return hexString.toString();
    }

    public static void main(String[] args) {
        System.out.println(encrypt(Bijection.getAdminToken()));
    }
}
