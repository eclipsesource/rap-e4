Eclipse4 applications on RAP
============================

**Update: This repository is outdated!** Eclipse 4 applications can run on RAP using the E4 support in the [RAP Incubator](https://www.eclipse.org/rap/incubator/).
See the [RAP/Eclipse4 wiki page](http://wiki.eclipse.org/RAP/Eclipse4) for details.

This is a spike to evaluate how E4 applications can run on RAP.
It's not working properly and does not even support multiple sessions.

Getting started
===============

* Setup an Eclipse IDE that contains the E4 SDK, EGit and the RAP Tools from Juno

* Import all projects from the `bundles/` directory

* Activate the target defined by `rap-e4.target` in the `targets/` project

* The example project `example.e4.app` contains a RAP launch configuration. Start it and point your browser to http://localhost:9090/e4example
