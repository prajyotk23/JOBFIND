const socket = new SockJS('/ws-chat');
const stompClient = Stomp.over(socket);

stompClient.connect({}, () => {
  stompClient.subscribe('/topic/message', (msg) => {
    const chat = JSON.parse(msg.body);
    const chatBox = document.getElementById("chatBox");
    const entry = document.createElement("div");
    entry.textContent = `${chat.sender}: ${chat.content}`;
    chatBox.appendChild(entry);
    chatBox.scrollTop = chatBox.scrollHeight;
  });
});

document.getElementById("helpForm").addEventListener("submit", async (e) => {
  e.preventDefault();

  const payload = {
    email: document.getElementById("email").value,
    reason: document.getElementById("reason").value
  };

  const res = await fetch("/api/support/request-unblock", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(payload)
  });

  const msg = await res.text();
  document.getElementById("helpMessage").textContent = msg;
});

function sendMessage() {
  const message = {
    sender: document.getElementById("email").value,
    content: document.getElementById("chatInput").value
  };
  stompClient.send("/app/SendMessage", {}, JSON.stringify(message));
  document.getElementById("chatInput").value = "";
}

