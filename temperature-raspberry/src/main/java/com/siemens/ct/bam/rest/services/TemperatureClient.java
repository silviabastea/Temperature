package com.siemens.ct.bam.rest.services;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class TemperatureClient {

    public String getContent() {

        Client client = Client.create();
        WebResource webResource = client.resource("http://www.meteoromania.ro/anm2/wp-content/plugins/meteo/json/situatia-meteo.php");
        ClientResponse response = webResource.get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code");
        }

        return response.getEntity(String.class);

    }
}
