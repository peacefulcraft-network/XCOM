package net.peacefulcraft.xcom.util.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpRequest.Builder;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import net.peacefulcraft.xcom.client.XCOMClient;

public class HttpUtilities {

	private static Gson gson = new Gson();
	private static JsonParser jsonParser = new JsonParser();
	private static HttpClient client;

	private static String token;

	public static void init(String token) {
		HttpUtilities.client = HttpClient.newBuilder()
			.followRedirects(HttpClient.Redirect.NEVER)
			.build();

		HttpUtilities.token = token;
	}

	protected static JsonElement jsonBodyParser(HttpResponse<String> response) throws RuntimeException {
		JsonObject obj;
		try {
			obj = jsonParser.parse(response.body()).getAsJsonObject();
		} catch (JsonSyntaxException ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		}

		if (response.statusCode() >= 200 && response.statusCode() <= 204) {
			return obj.get("data");
		
		} else if (response.statusCode() == 404) {
			return null;

		} else {
			throw new RuntimeException(obj.get("error").getAsString());
		}
	}

	protected static Builder standardBodyBuilderMiddleware(Builder b) {
		return b.setHeader("Authorization", token)
			.setHeader("User-Agent", "PeacefulCraft Bukkit Server/XCOM Client")
			/*
				Our development tool chain uses the PHP development server during test.
				The PHP development server does not like when you send the "Upgrade"
				header. We set the HTTP version explicitly to HTTP/1.1 so that
				the server does not discard our requests as invalid. 
			*/
			.version(Version.HTTP_1_1);
	}

	public static JsonElement getReq(String url) throws RuntimeException {
		HttpRequest req = standardBodyBuilderMiddleware(
			HttpRequest.newBuilder(URI.create(url))
			.GET()
		).build();

		HttpResponse<String> response;
		try {
			response = client.send(req, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException ex) {
			throw new RuntimeException("Error in HTTP request lifecycle.", ex);
		}
		XCOMClient._this().logDebug("Got HTTP response(" + url + "): " + response.body());
		return jsonBodyParser(response);
	}

	public static JsonElement postReq(String url, JsonElement data) throws RuntimeException {
		HttpRequest req = standardBodyBuilderMiddleware(
			HttpRequest.newBuilder(URI.create(url))
			.POST(BodyPublishers.ofString(gson.toJson(data)))
			.setHeader("Content-Type", "application/json")
		).build();

		HttpResponse<String> response;
		try {
			response = client.send(req, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException ex) {
			throw new RuntimeException("Error in HTTP request lifecycle.", ex);
		}
		XCOMClient._this().logDebug("Got HTTP response(" + url + "): " + response.body());
		return jsonBodyParser(response);
	}

	public static JsonElement putReq(String url, JsonElement data) throws RuntimeException {
		HttpRequest req = standardBodyBuilderMiddleware(
			HttpRequest.newBuilder(URI.create(url))
			.PUT(BodyPublishers.ofString(gson.toJson(data)))
			.setHeader("Content-Type", "application/json")
		).build();

		HttpResponse<String> response;
		try {
			response = client.send(req, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException ex) {
			throw new RuntimeException("Error in HTTP request lifecycle.", ex);
		}
		XCOMClient._this().logDebug("Got HTTP response(" + url + "): " + response.body());
		return jsonBodyParser(response);
	}

	public static JsonElement deleteReq(String url) throws RuntimeException {
		HttpRequest req = standardBodyBuilderMiddleware(
			HttpRequest.newBuilder(URI.create(url))
			.DELETE()
		).build();

		HttpResponse<String> response;
		try {
			response = client.send(req, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException ex) {
			throw new RuntimeException("Error in HTTP request lifecycle.", ex);
		}
		XCOMClient._this().logDebug("Got HTTP response(" + url + "): " + response.body());
		return jsonBodyParser(response);
	}
}
