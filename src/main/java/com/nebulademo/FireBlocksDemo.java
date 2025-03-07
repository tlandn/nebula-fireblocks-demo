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
  static String apiKey;
  static String secretKey;
  static Fireblocks fireblocks;

  static {
    apiKey = System.getenv().get("NEBULA_API_KEY");
    secretKey = System.getenv().get("NEBULA_SECRET_KEY");
    ConfigurationOptions configurationOptions = new ConfigurationOptions()
        .basePath(BasePath.Sandbox)
        .apiKey(apiKey)
        .secretKey(secretKey);
    fireblocks = new Fireblocks(configurationOptions);
  }

  public static void getPagedVaultAccounts() {
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

  public static void main(String[] args) {
    getPagedVaultAccounts();
  }
}
