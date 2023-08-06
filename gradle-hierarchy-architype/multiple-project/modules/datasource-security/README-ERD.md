Tables
* MEMBER
* MEMBERS_AUTHORITY
* AUTHORITY
* AUTHORITYS_FUNCTION
* FUNCTION

table dependencies
<pre>
┌ MEMBER ┐       ┌ MEMBERS_AUTHORITY ┐       ┌ AUTHORITY ┐       ┌ AUTHORITYS_FUNCTION ┐       ┌ FUNCTION ┐
│        │    ┌─ │                   │ ─┐    │           │    ┌─ │                     │ ─┐    │          │
│        │ ───┼─ │                   │ ─┼─── │           │ ───┼─ │                     │ ─┼─── │          │
│        │    └─ │                   │ ─┘    │           │    └─ │                     │ ─┘    │          │
└────────┘       └───────────────────┘       └───────────┘       └─────────────────────┘       └──────────┘
</pre>