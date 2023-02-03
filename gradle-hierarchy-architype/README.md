Multiple project architype

project descrption
* hello : example
* alpha : example
* beta : example
* alpha-persistence : jpa + mybatis
* beta-persistence : jpa + querydsl

project hierarchy 
<pre>
─ gradle-hierarchy-architype
  └ multiple-project
    ├ applications
    │ ├ hello
    │ ├ alpha
    │ └ beta
    ├ businesses
    │ └ greeting
    ├ cores
    │ ├ alpha-persistence
    │ └ beta-persistence
    └ modules
      ├ datasource-sample
      └ messagesource-sample
</pre>

package hierarchy 
<pre>
─ com   
  └ example
    │ /* com.example.applicaton.{application-name}.{...}.{...} */
    ├ application
    │ ├ hello
    │ ├ alpha
    │ └ beta
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
      └ messagesource
        └ sample
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
└────────────────────────────┘
┌ beta ──────────────────────┐
│ ┌ beta-persistence ──────┐ │
│ │ ┌ datasource-sample ─┐ │ │
│ │ │                    │ │ │
│ │ └────────────────────┘ │ │
│ └────────────────────────┘ │
└────────────────────────────┘
</pre>
