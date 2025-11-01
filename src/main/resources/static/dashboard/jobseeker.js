const token = localStorage.getItem("jwt");

document.getElementById("createForm").addEventListener("submit", async function (e) {
  e.preventDefault();

  const fileInput = document.getElementById("resumeFile");
  const file = fileInput.files[0];

  if (!file || file.type !== "application/pdf") {
    document.getElementById("createMessage").textContent = "❌ Please select a valid PDF resume.";
    return;
  }

  // Step 1: Upload resume to Cloudinary
  const formData = new FormData();
  formData.append("file", file);
  formData.append("upload_preset", "jobseeker_resume");

  let resumeURL = "";
  try {
    const cloudRes = await fetch("https://api.cloudinary.com/v1_1/dwlhtcmjm/upload", {
      method: "POST",
      body: formData
    });
    const cloudData = await cloudRes.json();
	console.log("Cloudinary response:", cloudData);
    resumeURL = cloudData.secure_url;
    if (!resumeURL) throw new Error("No URL returned");
  } catch (err) {
    console.error("Cloudinary error:", err);
    document.getElementById("createMessage").textContent = "❌ Resume upload failed.";
    return;
  }

  // Step 2: Submit profile to backend
  const dto = {
    fullName: document.getElementById("fullName").value,
    email: document.getElementById("email").value,
    phone: document.getElementById("phone").value,
    colleageName: document.getElementById("college").value,
    universityName: document.getElementById("university").value,
    degree: document.getElementById("degree").value,
    passingYear: document.getElementById("passingYear").value,
    resumeURL: resumeURL,
    active: document.getElementById("active").checked
  };

  fetch("/api/job_Seekers", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`
    },
    body: JSON.stringify(dto)
  })
    .then(res => {
      if (!res.ok) throw new Error("Profile creation failed");
      return res.json();
    })
    .then(data => {
      document.getElementById("createMessage").textContent = "✅ Profile created for " + data.fullName;
    })
    .catch(err => {
      console.error("Create error:", err);
      document.getElementById("createMessage").textContent = "❌ Profile creation failed.";
    });
});

// 🔍 Search by Email
function getByEmail() {
  const email = document.getElementById("searchEmail").value;
  fetch(`/api/job_Seekers/email/${email}`, {
    headers: { Authorization: `Bearer ${token}` }
  })
    .then(res => {
      if (!res.ok) throw new Error("Email search failed");
      return res.json();
    })
    .then(data => showModal(formatProfile(data)))
    .catch(err => {
      console.error("Email search error:", err);
      showModal("<p>❌ No profile found</p>");
    });
}

// 🔍 Search by ID
function getById() {
  const id = document.getElementById("searchId").value;
  fetch(`/api/job_Seekers/${id}`, {
    headers: { Authorization: `Bearer ${token}` }
  })
    .then(res => {
      if (!res.ok) throw new Error("ID search failed");
      return res.json();
    })
    .then(data => showModal(formatProfile(data)))
    .catch(err => {
      console.error("ID search error:", err);
      showModal("<p>❌ No profile found</p>");
    });
}

// 🧾 Format profile
function formatProfile(data) {
  if (!data || !data.email) return "<p>❌ No profile found</p>";

  const resumeURL = data.resumeURL || data.resumeurl || "";

  return `
    <p><strong>ID:</strong> ${data.id || "N/A"}</p>
    <p><strong>Full Name:</strong> ${data.fullName}</p>
    <p><strong>Email:</strong> ${data.email}</p>
    <p><strong>Phone:</strong> ${data.phone}</p>
    <p><strong>College:</strong> ${data.colleageName}</p>
    <p><strong>University:</strong> ${data.universityName}</p>
    <p><strong>Degree:</strong> ${data.degree}</p>
    <p><strong>Passing Year:</strong> ${data.passingYear}</p>

	<p><strong>Resume:</strong> 
	      ${resumeURL ? `
	        <a href="${resumeURL}" target="_blank" rel="noopener noreferrer">🔍 Open in New Tab</a> | 
	        <a href="${resumeURL}" download>⬇️ Download PDF</a>
	      ` : "❌ No resume uploaded"}
	    </p>



    <p><strong>Active:</strong> ${data.active ? "✅ Yes" : "❌ No"}</p>
  `;
}






// 🪟 Modal logic
function showModal(content) {
  document.getElementById("modalBody").innerHTML = content;
  document.getElementById("resultModal").style.display = "block";
}

function closeModal() {
  document.getElementById("resultModal").style.display = "none";
}
