# sbj-encrypt (Spring Boot Java - Encrypt)
Application to encrypt strings and files (checksum) to MD5, SHA1, SHA256 and SHA512

# swagger
http://localhost:9091/swagger-ui/index.html#/

# docker-build
docker build --no-cache -f Dockerfile -t sbj-encrypt:1.0.0 .

# docker-run
docker run -d --name encrypt -p 9091:9091 sbj-encrypt:1.0.0

# docker-rm
docker rm -f encrypt
