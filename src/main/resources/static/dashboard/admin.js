// 🔐 Role-based access check
const role = localStorage.getItem("role");
const token = localStorage.getItem("jwt");

if (role !== "ADMIN") {
  alert("❌ Access denied. Admins only.");
  window.location.href = "/login.html"; // adjust path if needed
} else {
  document.getElementById("adminDashboard").style.display = "block";
}

// 🚀 Perform BLOCK/UNBLOCK
function performAdminAction() {
  const dto = {
    adminId: parseInt(document.getElementById("adminId").value),
    userId: parseInt(document.getElementById("targetUserId").value),
    action: document.getElementById("adminAction").value
  };

  fetch("/api/admins/action", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`
    },
    body: JSON.stringify(dto)
  })
    .then(res => {
      if (!res.ok) throw new Error("Action failed");
      return res.json();
    })
    .then(data => {
      document.getElementById("adminActionMessage").textContent =
        `✅ ${data.action} performed by Admin ${data.adminId} on User ${data.userId}`;
    })
    .catch(err => {
      console.error("Admin action error:", err);
      document.getElementById("adminActionMessage").textContent = "❌ Action failed.";
    });
}

// 🔍 View logs by Admin
function getLogsByAdmin() {
  const adminId = document.getElementById("logAdminId").value;
  fetch(`/api/admins/admin/${adminId}`, {
    headers: { Authorization: `Bearer ${token}` }
  })
    .then(res => res.json())
    .then(data => showAdminLogs(data))
    .catch(err => {
      console.error("Admin log error:", err);
      showAdminLogs([]);
    });
}

// 🔍 View logs by User
function getLogsByUser() {
  const userId = document.getElementById("logUserId").value;
  fetch(`/api/admins/user/${userId}`, {
    headers: { Authorization: `Bearer ${token}` }
  })
    .then(res => res.json())
    .then(data => showAdminLogs(data))
    .catch(err => {
      console.error("User log error:", err);
      showAdminLogs([]);
    });
}

// 🪟 Modal display
function showAdminLogs(logs) {
  const modal = document.getElementById("adminLogModal");
  const body = document.getElementById("adminLogBody");

  if (!logs || logs.length === 0) {
    body.innerHTML = "<p>❌ No logs found</p>";
  } else {
    const html = logs.map(log => `
      <p><strong>Admin:</strong> ${log.adminId}</p>
      <p><strong>User:</strong> ${log.userId}</p>
      <p><strong>Action:</strong> ${log.action}</p>
      <p><strong>Time:</strong> ${new Date(log.timeStamp).toLocaleString()}</p>
      <hr>
    `).join("");
    body.innerHTML = html;
  }

  modal.style.display = "block";
}

function closeAdminLogModal() {
  document.getElementById("adminLogModal").style.display = "none";
}


window.addEventListener("DOMContentLoaded", () => {
  const user = JSON.parse(localStorage.getItem("loggedInUser"));
  if (user && user.role === "ADMIN") {
    loadUnblockRequests();
  }
});

function toggleUnblockRequests() {
  const section = document.getElementById("unblockRequests");
  if (section.style.display === "none") {
    section.style.display = "block";
    loadUnblockRequests(); // ✅ Fetch when opened
  } else {
    section.style.display = "none";
  }
}

async function loadUnblockRequests() {
  const res = await fetch("/api/support/requests");
  const requests = await res.json();

  const container = document.getElementById("unblockRequests");
  container.innerHTML = "";

  if (requests.length === 0) {
    container.innerHTML = "<p>No unblock requests found.</p>";
    return;
  }

  requests.forEach(req => {
    const card = document.createElement("div");
    card.className = "request-card";
    card.innerHTML = `
      <hr>
      <p><strong>Email:</strong> ${req.email}</p>
      <p><strong>Reason:</strong> ${req.reason}</p>
      <p><strong>Submitted:</strong> ${new Date(req.timestamp).toLocaleString()}</p>
    `;
    container.appendChild(card);
  });
}








