# CoffeeMaker SQA Testing Project ☕
## 📌 Overview
This repository contains my software quality assurance (SQA) testing project, developed as part of a rigorous testing curriculum. The main objective was to design and implement robust unit tests using **JUnit** to identify hidden bugs within a functional, yet intentionally flawed, Java application (CoffeeMaker).

## 🎯 The Testing Strategy: Golden vs. Buggy Models
To ensure the highest quality of testing, I utilized a comparative testing approach:
* **Golden Model Validation:** I initially verified my test suite against a "Golden" (perfect) version of the application to guarantee that all tests were logically sound and successfully passed.
* **Bug-Hunting in the Buggy Path:** After confirming the test suite's validity, I executed it against the "Buggy" version. The goal was achieved when the unit tests successfully failed, accurately pinpointing the injected defects in the system.

## 🏆 Key Achievements
* **100% Bug Detection Rate:** Successfully identified all hidden bugs in the buggy implementation.
* **Test-Driven Problem Solving:** Wrote targeted unit tests covering various edge cases and business logic constraints.

---

## 💻 Highlighted Test Cases & Bug Discovery

Here are two examples from my test suite demonstrating how I approached finding edge-case bugs:

### 1. Boundary Value Analysis (Recipe Limit)
The system is designed to hold a maximum of 3 recipes. I wrote a test to verify that attempting to add a 4th recipe is correctly handled and rejected. In a buggy implementation, the system might crash or incorrectly overwrite existing data when this boundary is crossed.

```java
@Test
public void testAddRecipeLimit() {
    cm.addRecipe(r1);
    cm.addRecipe(r2);
    cm.addRecipe(r3);
    // Verifying that the 4th recipe is not added (limit is 3)
    assertFalse("The 4th recipe should not be added", cm.addRecipe(r4)); 
}
2. State Verification & Negative Testing (Delete Recipe)
Deleting an item changes the system's state. My test ensures that deleting an existing recipe returns its correct name, but more importantly, it checks the negative path: attempting to delete from an already empty slot must gracefully return null instead of throwing an unhandled exception.

@Test
public void testDeleteRecipe() {
    // Assuming a recipe named "Coffee" was added at index 0 during setUp()
    assertEquals("Coffee", cm.deleteRecipe(0));
    
    // Attempting to delete from the now-empty slot should return null
    assertNull("Deleting an empty slot must return null", cm.deleteRecipe(0));
}
🧠 Challenges & Lessons Learned
Throughout this testing process, I encountered several practical challenges that provided invaluable lessons in Software Quality Assurance:

The Butterfly Effect of a Single Bug: I realized firsthand how a seemingly insignificant detail—such as an unhandled boundary condition or a single missed validation—can deform the logic of the entire application architecture. A small bug in one method can easily cause cascading failures elsewhere.

Strict File Structuring: One of the initial hurdles was understanding the strict requirements of automated grading systems. This taught me that maintaining absolute precision in package naming (e.g., edu.ncsu.csc326.coffeemaker) and directory structures is just as critical as writing the test logic itself.

Shifting the Testing Mindset: The biggest mental shift was moving away from writing tests that simply confirm the code "works" (Golden path), to actively designing tests with the sole intention of breaking the system and exposing vulnerabilities.

⚙️ The Power of JUnit in Complex Systems
This project heavily reinforced why unit testing, specifically using a framework like JUnit, is indispensable for modern software development and quality assurance:

Granular Isolation: In complex platforms, tracking down a system-wide bug can be incredibly time-consuming. JUnit allows QA engineers to isolate and test individual units (methods) of code independently. This micro-level testing ensures that each component works flawlessly on its own before being integrated into the larger ecosystem.

Automated Safety Net: Writing detailed unit tests creates a permanent, automated safety net. It guarantees that any future updates, refactoring, or new feature additions won't silently break existing, complex business logic.

Edge-Case Precision: Frameworks like JUnit make it seamless to programmatically simulate extreme edge cases, negative paths, and boundary conditions (such as exceeding inventory limits or mishandling null values) that would be practically impossible to cover reliably through manual testing alone.
