package richo.testproject.docker.spotify;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;

public class SpotifyDockerMain
{
	public static void main(String[] args) throws Exception
	{
		final DockerClient docker = DefaultDockerClient.fromEnv().build();

		// Pull an image
		System.out.println("pulling");
		docker.pull("alpine:latest");
		System.out.println("##done!");
		docker.close();
		System.out.println("closed");
	}
}
