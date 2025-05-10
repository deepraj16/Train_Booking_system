const API_URL = "http://localhost:8050/login";

        // Function to get the next ID
        function getNextId() {
            return fetch(API_URL)
                .then(response => response.json())
                .then(users => {
                    if (users.length === 0) return 1; // If no users, start from 1
                    const maxId = Math.max(...users.map(user => user.id));
                    return maxId + 1; // Increment ID
                })
                .catch(error => {
                    console.error("Error fetching users:", error);
                    return 1; // Default ID
                });
        }

        // Create Account (Sign Up)
        function createAccount() {
            const username = document.getElementById("newUsername").value;
            const password = document.getElementById("newPassword").value;

            if (!username || !password) {
                alert("Please enter username and password!");
                return;
            }

            getNextId().then(newId => {
                fetch(API_URL, {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify({ id: newId, username, password })
                })
                .then(response => response.json())
                .then(data => {
                    alert("Account Created Successfully!");
                    document.getElementById("newUsername").value = "";
                    document.getElementById("newPassword").value = "";
                    window.location.href = "index.html";
                })
                .catch(error => console.error("Error creating account:", error));
            });
        }


        function login() {
            const username = document.getElementById("loginUsername").value;
            const password = document.getElementById("loginPassword").value;

            fetch(API_URL)
                .then(response => response.json())
                .then(users => {
                    const user = users.find(u => u.username === username && u.password === password);
                    if (user) {
                        alert("Login Successful!");
                        window.location.href = "index.html";
                    } else {
                        alert("Invalid Username or Password");
                       
                    }
                })
                .catch(error => console.error("Error:", error));
        }
