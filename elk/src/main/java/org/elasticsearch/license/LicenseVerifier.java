package org.elasticsearch.license;

public class LicenseVerifier
{
    public static boolean verifyLicense(final License license, final byte[] encryptedPublicKeyData) {
        return true;
    }
    
    public static boolean verifyLicense(final License license) {
        return true;
    }
}

