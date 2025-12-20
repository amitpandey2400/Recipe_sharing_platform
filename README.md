# Vedic Bytes – Recipe Platform

A Java‑based recipe management platform that started as a console application and is being evolved into a full web app using Servlets and JSP.

> Status: Core business logic is complete and tested in the console version; Servlet + JSP web layer is implemented in code and ready to be wired to a production‑ready Tomcat setup.

---

## 1. Project Overview

This project lets users register, log in, and browse/search recipes backed by a reusable `Platform` core.  
The architecture is intentionally layered so the same business logic can be reused from both console and web front‑ends.

### Main capabilities

- User registration and login (basic auth flow)
- Browse all recipes
- Search recipes by title / ingredients / category
- Simple persistence using a data file (`platform_data.dat`)
- Separation of concerns:
  - **Core**: `platform` package (models, services, persistence)
  - **Web UI**: JSP pages
  - **Web layer**: Servlets

---

## 2. Project Structure
RecipePlatform/
├─ src/
│ ├─ platform/
│ │ ├─ models/ # Recipe, User and other domain models
│ │ ├─ utils/ # Helpers (e.g. loading/saving data)
│ │ ├─ Platform.java # Core application façade
│ │ └─ AuthService.java # Authentication / session logic
│ └─ web/
│ ├─ AppInitializer.java # Bootstraps Platform on server startup
│ ├─ LoginServlet.java # Handles /login
│ ├─ RegisterServlet.java # Handles /register
│ ├─ BrowseRecipesServlet.java # Handles /recipes (list)
│ ├─ SearchRecipesServlet.java # Handles /search
│ └─ UserMenuServlet.java # Simple post‑login navigation
│
├─ web/
│ ├─ WEB-INF/
│ │ └─ web.xml # Deployment descriptor (Servlet/JSP mappings)
│ └─ jsp/
│ ├─ login.jsp
│ ├─ register.jsp
│ ├─ recipes.jsp
│ ├─ search.jsp
│ └─ user_menu.jsp
│
├─ platform_data.dat # Sample data file (recipes + users)
├─ .gitignore
└─ README.md # You are here


---

## 3. Features in Detail

### 3.1 Core platform (console‑agnostic)

The `platform` package is fully independent of any UI framework so it can be reused from CLI, Swing, or Servlets.

Key responsibilities:

- **Recipe management**
  - Load recipes from `platform_data.dat`
  - List all recipes
  - Filter recipes (by name, ingredient, category)
- **User management**
  - Model user accounts with basic fields (e.g. username, password)
  - Registration and login using `AuthService`
- **Persistence**
  - Read/write data from/to `platform_data.dat`
  - Simple file‑based approach suitable for demos and small projects

All UI layers talk to the platform only through a small, well‑defined API (`Platform` façade plus `AuthService`).

---

### 3.2 Web UI (JSP pages)

The `web/jsp` folder contains presentation‑only JSPs; business logic lives in the `platform` package and is accessed via Servlets.

Pages:

- `login.jsp`
  - Form for username/password
  - Shows login errors when credentials are invalid
- `register.jsp`
  - Registration form
  - Displays basic validation feedback
- `recipes.jsp`
  - Lists recipes passed from `BrowseRecipesServlet`
  - Simple table‑style layout
- `search.jsp`
  - Form to search/filter recipes
  - Shows filtered results
- `user_menu.jsp`
  - Simple navigation hub after login (e.g. “Browse Recipes”, “Search”, “Logout”)

All JSPs are designed to be as “dumb” as possible, rendering data that the Servlets put into request attributes.

---

### 3.3 Web layer (Servlets)

The `src/web` package integrates the core platform with the JSP pages.

- `AppInitializer`
  - Runs on server startup
  - Instantiates `Platform`
  - Loads data from `platform_data.dat`
  - Stores the shared `Platform` instance in `ServletContext`
- `LoginServlet`
  - `GET /login` → show `login.jsp`
  - `POST /login` → authenticate via `AuthService`; on success, create HTTP session and redirect to user menu
- `RegisterServlet`
  - `GET /register` → show `register.jsp`
  - `POST /register` → create a new user in the platform and redirect to login
- `BrowseRecipesServlet`
  - `GET /recipes` → fetch all recipes from `Platform`, forward to `recipes.jsp`
- `SearchRecipesServlet`
  - `GET /search` → show search form
  - `POST /search` → run search on `Platform`, forward results to `search.jsp`
- `UserMenuServlet`
  - `GET /menu` → simple logged‑in landing page

A small `PlatformAware` helper interface (or utility methods) is used so each Servlet can easily get the shared `Platform` object from the servlet context.

---

## 4. How to Run (console version)

The core platform can be demonstrated via the existing console entry point.

1. Compile:
2. cd RecipePlatform/src
javac MainGUI.java Platform.java platform/**/*.java

text


2. Run:

java MainGUI

or whichever `main` class you prefer for the console demo (e.g. `Main`).

> Note: A full web deployment with Tomcat 11 + proper build tooling (e.g. Maven) is planned, so the Servlets/JSPs are currently best viewed as a “ready‑to‑integrate” web layer over the working platform core.

---

## 5. Possible Improvements / Roadmap

- Convert project to a standard Maven **war** project (`src/main/java`, `src/main/webapp`)
- Add proper password hashing and validation
- Replace file‑based storage with a relational database (JDBC / JPA)
- Add categories, ratings, and favorites for recipes
- REST API layer to expose recipes for frontend frameworks or mobile apps

---

## 6. License

Add your preferred license here (for example, MIT):


MIT License – see LICENSE file for details.

undefined






