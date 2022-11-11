Multiple project architype

project hierarchy   
─ gradle-hierarchy-architype   
  └ multiple-project   
    ├ applications   
    │ └ hello   
    ├ cores   
    │ └ hello-persistence   
    ├ businesses   
    │ └ greeting   
    └ modules   
     └ datasource-memory   

package hierarchy   
─ com   
  └ example   
    │ /* com.example.applicaton.{application-name}.{...}.{...} */   
    ├ application   
    │ └ hello   
    │   
    │ /* 'core' is application dependent */   
    │ /* com.example.cores.{application-name}.{module-name}.{...}.{...} */   
    ├ cores   
    │ └ hello   
    │   ├ jpa   
    │   └ mybatis   
    │   
    │ /* com.example.businesses.{business-name}.{...}.{...} */   
    ├ businesses   
    │ └ greeting   
    │   
    │ /* com.example.modules.{module-name}.{...}.{...} */   
    └ modules   
      └ datasource   
        └ memory   

