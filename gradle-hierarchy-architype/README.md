Multiple project architype

project hierarchy 
<pre>
─ gradle-hierarchy-architype
  └ multiple-project
    ├ applications
    │ ├ hello
    │ └ sample
    ├ businesses
    │ └ greeting
    ├ cores
    │ ├ sample-persistence
    │ └ sample-querydsl-persistence
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
    │ └ sample
    │
    │ /* 'business' should work independently */
    │ /* com.example.businesses.{business-name}.{...}.{...} */
    ├ businesses
    │ └ greeting
    │
    │ /* 'core' is application dependent */
    │ /* com.example.cores.{application-name}.{module-name}.{...}.{...} */
    ├ cores
    │ └ sample
    │   ├ jpa
    │   └ mybatis
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
┌ sample ─────────────────────────────┐
│ ┌ sample-querydsl-persistence ────┐ │
│ │ ┌ datasource-sample ─┐          │ │
│ │ │                    │          │ │
│ │ └────────────────────┘          │ │
│ └─────────────────────────────────┘ │
└─────────────────────────────────────┘
</pre>
