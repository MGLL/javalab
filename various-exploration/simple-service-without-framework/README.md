# simple-service-without-framework

The objective of this project is to play around and create a small App without using a framework.  

**Exploration objective / idea:**
- The App should be able to manage a simple representation of books
- The App should be composed of
  - Data Access Layer (DAL) - Using In Memory first for simplicity.
  - "Business Logic" (BL) - _Service layer_
  - Ideally, (System) Interface (SI) - Simple endpoints
- Everything should be done following TDD: Write _(failing)_ tests first and then implement.
- Everything should be done following Clean-Code _(at least, try)_.
- At the end, dependencies should be provided through an ApplicationContext with methods.
