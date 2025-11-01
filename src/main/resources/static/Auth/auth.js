// 🔄 Populate role dropdown dynamically
const roles = ["JOBSEEKER", "RECRUITER", "ADMIN"];
const roleSelect = document.getElementById("role");
if (roleSelect) {
  roles.forEach(role => {
    const option = document.createElement("option");
    option.value = role;
    option.textContent = role.charAt(0) + role.slice(1).toLowerCase();
    roleSelect.appendChild(option);
  });
}

// 🔐 Registration
document.getElementById("registerForm")?.addEventListener("submit", async (e) => {
  e.preventDefault();

  const registerBtn = document.getElementById("registerBtn");
  const spinner = document.getElementById("spinner");

  // Disable button and show spinner
  registerBtn.disabled = true;
  spinner.style.display = "inline";

  const payload = {
    userName: document.getElementById("userName").value,
    userEmail: document.getElementById("userEmail").value,
    password: document.getElementById("password").value,
    role: document.getElementById("role").value
  };

  try {
    const res = await fetch("/api/auth/register", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload)
    });

    const data = await res.json();

    if (data.message === "User already registered") {
      showMessage("registerMessage", "❗ User already registered", "orange");
    } else if (res.ok) {
      localStorage.setItem("jwt", data.token);
      showMessage("registerMessage", "✅ Registered successfully", "green");
      setTimeout(() => {
        window.location.href = "/auth/login.html";
      }, 3000);
    } else {
      showMessage("registerMessage", "⚠️ Registration failed", "red");
    }
  } catch (err) {
    console.error("Registration error:", err);
    showMessage("registerMessage", "⚠️ Something went wrong", "red");
  } finally {
    // Re-enable button and hide spinner
    registerBtn.disabled = false;
    spinner.style.display = "none";
  }
});











// 🔑 Login
document.getElementById("loginForm")?.addEventListener("submit", async (e) => {
  e.preventDefault();

  const loginBtn = document.getElementById("loginBtn");
  const spinner = document.getElementById("loginSpinner");

  loginBtn.disabled = true;
  spinner.style.display = "inline";

  const payload = {
    userEmail: document.getElementById("loginEmail").value.trim().toLowerCase(),
    password: document.getElementById("loginPassword").value
  };

  try {
	const res = await fetch("/api/auth/login", {
	  method: "POST",
	  headers: { "Content-Type": "application/json" },
	  body: JSON.stringify(payload)
	});


	if (!res.ok) {
	  const errorText = await res.text();
	  console.log("Login failed:", res.status, errorText); // ✅ Log it

	  if (res.status === 403 && errorText.toLowerCase().includes("blocked")) {
		showMessage("loginMessage", `🚫 Your account is blocked. <a href="/support/help.html" style="color:blue;text-decoration:underline;">Request Help</a>`, "red");

	  } else if (res.status === 401 || errorText.toLowerCase().includes("invalid")) {
	    showMessage("loginMessage", "❌ Invalid credentials", "red");
	  } else {
	    showMessage("loginMessage", "⚠️ Login failed", "orange");
	  }

	  return;
	}



    const token = await res.text(); // assuming login returns plain token
    localStorage.setItem("jwt", token);
    showMessage("loginMessage", "✅ Login successful", "green");

    // ✅ Decode token and extract role BEFORE setTimeout
	const decoded = JSON.parse(atob(token.split('.')[1]));
	const role = decoded.role;
	localStorage.setItem("role", role); 

    setTimeout(() => {
      if (role === "JOBSEEKER") {
        window.location.href = "/dashboard/jobseeker.html";
      } else if (role === "RECRUITER") {
        window.location.href = "/dashboard/recruiter.html";
      } else if (role === "ADMIN") {
        window.location.href = "/dashboard/admin.html";
      } else {
        window.location.href = "/dashboard.html"; // fallback
      }
    }, 2000);
  } catch (err) {
    console.error("Login error:", err);
    showMessage("loginMessage", "⚠️ Something went wrong", "red");
  } finally {
    loginBtn.disabled = false;
    spinner.style.display = "none";
  }
});

localStorage.setItem("loggedInUser", JSON.stringify({
  email: user.email,
  role: user.role
}));





// 🧠 Utility: Show and auto-hide message
function showMessage(id, text, color) {
  const el = document.getElementById(id);
  el.innerHTML = text; // ✅ Renders HTML like <a> tags
  el.style.color = color;
  el.style.display = "block";

  setTimeout(() => {
    el.style.display = "none";
  }, 10000);
}



