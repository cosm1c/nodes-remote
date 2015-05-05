Nodes Remote
============

h1. Docker run command

`docker run --rm --link hub:hub nodes-remote:1.0-SNAPSHOT X a`

For an interactive shell:

`docker run -it --entrypoint=/bin/bash --rm --link hub:hub nodes-remote:1.0-SNAPSHOT`
