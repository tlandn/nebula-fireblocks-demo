package com.nebulademo;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

import com.fireblocks.sdk.ApiException;
import com.fireblocks.sdk.ApiResponse;
import com.fireblocks.sdk.BasePath;
import com.fireblocks.sdk.ConfigurationOptions;
import com.fireblocks.sdk.Fireblocks;
import com.fireblocks.sdk.model.VaultAccountsPagedResponse;
import com.nebulademo.utils.JSONUtils;

public class FireBlocksDemo {
  Fireblocks fireblocks;

  public static FireBlocksDemo getInstance(String apiKey, String secretKey) {
    ConfigurationOptions configurationOptions = new ConfigurationOptions()
        .basePath(BasePath.Sandbox)
        .apiKey(apiKey)
        .secretKey(secretKey);
    FireBlocksDemo fireBlocksDemo = new FireBlocksDemo();
    fireBlocksDemo.fireblocks = new Fireblocks(configurationOptions);

    return fireBlocksDemo;
  }

  public void getPagedVaultAccounts() {
    // Retrieving Vault Accounts
    BigDecimal limit = new BigDecimal(10);
    try {
      CompletableFuture<ApiResponse<VaultAccountsPagedResponse>> response = fireblocks.vaults()
          .getPagedVaultAccounts(null, null, null, null, null, null, null, limit);
      // System.out.println("Status code: " + response.get().getStatusCode());
      // System.out.println("Response headers: " + response.get().getHeaders());
      // System.out.println("Response body: " + response.get().getData());
      System.out.println(JSONUtils.toJSON(response.get().getData()));
    } catch (Exception e) {
      ApiException apiException = (ApiException) e.getCause();
      System.err.println("Exception when calling VaultsApi#getPagedVaultAccounts");
      System.err.println("Status code: " + apiException.getCode());
      System.err.println("Response headers: " + apiException.getResponseHeaders());
      System.err.println("Reason: " + apiException.getResponseBody());
      e.printStackTrace();
    }
  }

  public void newMethod2() {
    System.out.println("New Method 2");
  }

  public static void main(String[] args) {
    String apiKey = System.getenv().get("NEBULA_API_KEY");
    String secretKey = System.getenv().get("NEBULA_SECRET_KEY");
    var fireblocksDemo = FireBlocksDemo.getInstance(apiKey, secretKey);
    fireblocksDemo.getPagedVaultAccounts();
  }
}
