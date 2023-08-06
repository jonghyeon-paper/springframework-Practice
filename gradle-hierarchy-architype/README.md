Multiple project architype

project description
* hello : simple example

* alpha : example of multiple project configurations
  * WAS config
  * version prefix apply to URI path
  * datetime format config of object-mapper

* beta : example of multiple project configurations
  * WAS config

* gamma : example of session based spring-security
  * WAS config
  * version prefix apply to URI path
  * spring-security config
  * thymeleaf config
  * jquery and bootstrap
  * demo code
  * see '/gamma/README.md' file

* greeting : simple example

* alpha-persistence : example
  * using hibernate(jpa)
  * using mybatis

* beta-persistence : example
  * using hibernate(jpa)
  * using querydsl

* gamma-persistence : example
  * using hibernate(jpa)

* datasource-sample : simple example
  * using memory database

* datasource-security : authentication and authorization data
  * using memory database
  * see '/datasource-security/README-ERD.md' file

* messagesource-sample : simple example
  * spring messagesource

* openapi : documentation support
  * using swagger
  * see '/openapi/README-ADDRESS.md' file


project hierarchy 
<pre>
─ gradle-hierarchy-architype
  └ multiple-project
    ├ applications
    │ ├ hello
    │ ├ alpha
    │ ├ beta
    │ └ gamma
    ├ businesses
    │ └ greeting
    ├ cores
    │ ├ alpha-persistence
    │ ├ beta-persistence
    │ └ gamma-persistence
    └ modules
      ├ datasource-sample
      ├ datasource-security
      ├ messagesource-sample
      └ openapi
</pre>

package hierarchy 
<pre>
─ com   
  └ example
    │ /* com.example.applicaton.{application-name}.{...}.{...} */
    ├ application
    │ ├ hello
    │ ├ alpha
    │ ├ beta
    │ └ gamma
    │
    │ /* 'business' should work independently */
    │ /* com.example.businesses.{business-name}.{...}.{...} */
    ├ businesses
    │ └ greeting
    │
    │ /* 'core' is application dependent */
    │ /* com.example.cores.{application-name}.{module-name}.{...}.{...} */
    ├ cores
    │ ├ alpha
    │ │ ├ jpa
    │ │ └ mybatis
    │ ├ beta
    │ │ └ jpa
    │ └ gamma
    │   └ jpa
    │
    │ /* com.example.modules.{module-name}.{...}.{...} */
    └ modules
      ├ datasource
      │ ├ sample
      │ └ security
      ├ messagesource
      │ └ sample
      └ openapi
</pre>

application dependencies
<pre>
┌ hello ────────┐
│ ┌ greeting ─┐ │
│ │           │ │
│ └───────────┘ │
└───────────────┘
┌ alpha ─────────────────────┐
│ ┌ alpha-persistence ─────┐ │
│ │ ┌ datasource-sample ─┐ │ │
│ │ │                    │ │ │
│ │ └────────────────────┘ │ │
│ └────────────────────────┘ │
│ ┌ openapi ─┐               │
│ │          │               │
│ └──────────┘               │
└────────────────────────────┘
┌ beta ──────────────────────┐
│ ┌ beta-persistence ──────┐ │
│ │ ┌ datasource-sample ─┐ │ │
│ │ │                    │ │ │
│ │ └────────────────────┘ │ │
│ └────────────────────────┘ │
└────────────────────────────┘
┌ gamma ───────────────────────┐
│ ┌ gamma-persistence ───────┐ │
│ │ ┌ datasource-security ─┐ │ │
│ │ │                      │ │ │
│ │ └──────────────────────┘ │ │
│ └──────────────────────────┘ │
│ ┌ openapi ─┐                 │
│ │          │                 │
│ └──────────┘                 │
└──────────────────────────────┘
</pre>
