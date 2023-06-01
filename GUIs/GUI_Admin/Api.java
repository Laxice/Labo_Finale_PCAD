package GUI_Admin;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Api {
    private static final String USER_AGENT = "Mozilla/5.0";
	private static final String BASE_URL = "http://localhost:4567/";

    public String listaEventi() throws IOException {
		URL obj = new URL(BASE_URL+"eventi");
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			return response.toString();
		} else {
			return "GET request did not work.";
		}
	}

	public void crea(String nome, String posti) throws IOException {
		URL obj = new URL(BASE_URL+"eventi");
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		String POST_PARAMS = "nome="+nome+"&posti="+posti;
		byte[] postData       = POST_PARAMS.getBytes( StandardCharsets.UTF_8 );
		int    postDataLength = postData.length;
		System.out.println(POST_PARAMS);
		// For POST only - START
		con.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
		con.setRequestProperty( "charset", "utf-8");
		con.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(postData);
		os.flush();
		os.close();
		// For POST only - END

		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("POST request did not work.");
		}
	}

	public void aggiungi(String nome, String posti) throws IOException {
		URL obj = new URL(BASE_URL+"eventi");
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("PUT");
		con.setRequestProperty("User-Agent", USER_AGENT);
		String POST_PARAMS = "nome="+nome+"&posti="+posti;
		byte[] postData       = POST_PARAMS.getBytes( StandardCharsets.UTF_8 );
		int    postDataLength = postData.length;
		System.out.println(POST_PARAMS);
		// For POST only - START
		con.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
		con.setRequestProperty( "charset", "utf-8");
		con.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(postData);
		os.flush();
		os.close();
		// For POST only - END

		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("POST request did not work.");
		}
	}
	public void chiudi(String nome) throws IOException {
		URL obj = new URL(BASE_URL+"eventi?nome="+nome);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("DELETE");
		con.setRequestProperty("User-Agent", USER_AGENT);
		
		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("POST request did not work.");
		}
	}
}
