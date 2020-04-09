FROM openjdk:11-jdk-stretch@sha256:04b4670ac7e90789932320ba849bda607d4edaba812b28570e946dd447e85041

RUN useradd -m build

RUN mkdir /target
RUN chown build:build /target
VOLUME /target

WORKDIR /home/build/app
COPY sbt build.sbt ./
COPY project/build.properties project/plugins.sbt ./project/
RUN chown -R build:build .

USER build
RUN ./sbt update
