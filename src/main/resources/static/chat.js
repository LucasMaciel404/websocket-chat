let stompClient = null;

document.getElementById('connectBtn').addEventListener('click', () => {
    const token = document.getElementById('token').value.trim();
    if (!token) {
        alert('Digite o token JWT');
        return;
    }

    const socket = new SockJS('http://localhost:8080/ws?token=' + token);
    stompClient = Stomp.over(socket);

    stompClient.connect({}, frame => {
        console.log('Conectado:', frame);
        document.getElementById('sendBtn').disabled = false;
        document.getElementById('checkBtn').disabled = false;
        updateStatus('Conectado');

        // Receber mensagens privadas
        stompClient.subscribe('/user/queue/messages', msg => {
            const message = JSON.parse(msg.body);
            showMessage(`${message.from}: ${message.content}`);
        });
    }, error => {
        console.error('Erro de conexão:', error);
        updateStatus('Desconectado');
        alert('Falha ao conectar. Verifique o token e o servidor.');
    });
});

// Enviar mensagem
document.getElementById('sendBtn').addEventListener('click', () => {
    if (!stompClient || !stompClient.connected) {
        alert('Você precisa se cone
