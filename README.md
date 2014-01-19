misc-configurator
=================

Configuration decoupler

A utility that uses java proxies to decouple configurations from modules. In most enterprise applications, all modules are coupled with a common configuration class. This often causes problems when you need to decompose the application in to separate domains.

This project has a lightweight workable approach where each module defines an interface whose methods are annotated (any annotation whose simple name is Bind with a value attribute). The 'value' attribute contains the property key that is returned when the interface proxy is called.

This project is NOT suitable to run AS-IS in production. There needs to be a lot of error checks and default conversions, but it provides a starting point for those wishing to reduce compile time coupling to common components.
