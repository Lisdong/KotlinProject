// IWebviewService.aidl
package com.example.kt;

// Declare any non-default types here with import statements
import com.example.kt.bean.User;
interface IWebviewService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    User getUserInfo();
}
