package se.bth.softhouse.resources;

import java.io.InputStream;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import se.bth.softhouse.entities.Filter;
import se.bth.softhouse.process.FilterProcess;

@Produces(MediaType.APPLICATION_JSON)
@Path("audio")
public class AudioResource {


	public AudioResource() {
	}
	
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public void uploadAudioFile(@FormDataParam("file") final InputStream fileInputStream,
	        @FormDataParam("file") final FormDataContentDisposition contentDispositionHeader) {
		System.out.println(contentDispositionHeader.getFileName());
	}
}
