Multiple project architype

project description
* hello : example
* alpha : example
  * WAS config
  * version prefix apply to URI path
  * datetime format config of object-mapper 
* beta : example
  * WAS config
* gamma : example
  * something todo...
* alpha-persistence : example
  * jpa
  * mybatis
* beta-persistence : example
  * jpa
  * querydsl


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
    │ └ beta-persistence
    └ modules
      ├ datasource-sample
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
    │ └ beta
    │   └ jpa
    │
    │ /* com.example.modules.{module-name}.{...}.{...} */
    └ modules
      ├ datasource
      │ └ sample
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
│ │ ┌ openapi ─┐           │ │
│ │ │          │           │ │
│ │ └──────────┘           │ │
│ └────────────────────────┘ │
└────────────────────────────┘
┌ beta ──────────────────────┐
│ ┌ beta-persistence ──────┐ │
│ │ ┌ datasource-sample ─┐ │ │
│ │ │                    │ │ │
│ │ └────────────────────┘ │ │
│ └────────────────────────┘ │
└────────────────────────────┘
┌ gamma ─────────────────────┐
│ ┌ ?-persistence ──────┐ │
│ │ ┌ ?-sample ─┐ │ │
│ │ │                    │ │ │
│ │ └────────────────────┘ │ │
│ └────────────────────────┘ │
└────────────────────────────┘
</pre>
