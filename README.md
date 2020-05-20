# ThingsBoard Custom OAuth 2.0 mapper

This project provides a sample of custom mapper of OAuth 2.0 external user info into ThingsBoard internal user object.

You can use this application as a base implementation for the Custom Mapper endpoint.
As well, you can copy any snippet of this implementation into already existent your microservices.

You can combine this Custom Mapper with [ThingsBoard REST API Client](https://thingsboard.io/docs/reference/rest-client/) to get any additional information from the ThingsBoard platform as Tenant Id or Customer Id.   

## Prerequisites

- [Install Docker CE](https://docs.docker.com/engine/installation/)

## Running

To run test against ThingsBoard first create plain text file to set up test configuration (in our example configuration file name is *.env*):
```bash
touch .env
```

Edit this *.env* file:
```bash
nano .env
```

and put next content into the text file (modify it according to your test goals):
```bash
CUSTOM_MAPPER_USERNAME=admin
CUSTOM_MAPPER_PASSWORD={noop}password
```

Where: 
    
- `CUSTOM_MAPPER_USERNAME`       - Login of the user 
- `CUSTOM_MAPPER_PASSWORD`       - Password of the user (`{noop}` is just a configuration and not a part of the password)

Once params are configured to run test simple type from the folder where configuration file is located:
```bash
docker run -it --env-file .env -p 10010:10010 --name tb-oauth2-mapper thingsboard/oauth2-mapper
```
