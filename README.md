# SpringBootParticleSim

A simple particle simulation excersise to practice coding in Java with SpringBoot and gradle.

## Running the app

Clone this project onto a machine with JDK11.
Run

```sh
./gradlew test
```

for tests, and

```sh
./gradlew bootRun
```

for the application itself.
For now the options are hardcoded inside the MainPanel.java file. There you can update the maximum framerate (set to 60), as well as the amount of particles spawned in, their speed, and the angle (there is a strange behaviour where the particles basically "batch" themselves into groups at certain angles, but that should be addressed in the roadmap features)

## Roadmap

* Add window resizing capability
* Add ability to spawn particles by hand in batches
* Add colour options accessible through GUI
* Add "brush" options on gui for particles batch sizes
* Add ability to spawn in an individual particle by clicking and dragging to set angle and speed (scroll wheel could also set mass).
* Add ability to insert gravitional masses
* Basically turn this into the NOITA engine...
