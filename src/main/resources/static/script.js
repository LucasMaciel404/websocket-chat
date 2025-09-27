let stompClient = null;

let nome = document.querySelector("#name");
let tokenInput = document.querySelector("#token");

let btnConnect = document.querySelector("#connect");
let btnDisconnect = document.querySelector("#disconnect");
let btnSend = document.querySelector("#send");

function connect() {
  try {
    let token = tokenInput.value.trim();
    if (!token) {
      alert("Token é obrigatório!");
      return;
    }

    const socket = new SockJS("http://localhost:8080/ws?token=" + token);
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {
      console.log("Conectado: " + frame);
      alert("Conectado com sucesso!");

      // Assina a fila pessoal do usuário
      stompClient.subscribe("/user/queue/messages", function (messageOutput) {
        const msg = JSON.parse(messageOutput.body);
        console.log("📩 Mensagem recebida:", msg);

        // let div = document.querySelector("#messages");
        // let p = document.createElement("p");
        // p.textContent = msg.from + ": " + msg.content;
        // div.appendChild(p);
      });
    });
  } catch (error) {
    alert("Erro ao conectar!");
    console.error(error);
  }
}

function disconnect() {
  try {
    if (stompClient !== null) {
      stompClient.disconnect(() => {
        console.log("Desconectado com sucesso!");
        alert("Desconectado do servidor!");
      });
      stompClient = null;
    } else {
      alert("Nenhuma conexão ativa!");
    }
  } catch (error) {
    alert("Erro ao desconectar!");
    console.error(error);
  }
}

function sendMessageTo() {
  let message = {
    to: document.querySelector("#to").value,        // destino
    from: nome.value,                               // remetente
    content: document.querySelector("#content").value // mensagem
  };

  if (stompClient && stompClient.connected) {
    stompClient.send("/app/private-message", {}, JSON.stringify(message));
    console.log("Mensagem privada enviada:", message);
  } else {
    console.error("❌ Não conectado ao servidor WebSocket");
  }
}

// Adiciona eventos no final
btnConnect.addEventListener("click", connect);
btnDisconnect.addEventListener("click", disconnect);
btnSend.addEventListener("click", sendMessageTo);
