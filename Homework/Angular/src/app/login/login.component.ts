import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import axios from "axios";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  standalone: true,
  imports: [FormsModule],
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(private router: Router) {}

  login(): void {
    const htmlbody = {
      username: this.username,
      password: this.password
    };

    // axios.post("http://localhost:80/lab06-web/login.php", htmlbody)
    //   .then(response => console.log(response.data));

    let redirect = "";
    let request = new XMLHttpRequest();
    request.open("POST", "http://localhost/lab06-web/login.php", false);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = function () {
      if (request.readyState === 4 && request.status === 200) {
        let response = JSON.parse(request.responseText)
        if (response.success) {
          alert(response.message);
          sessionStorage.setItem('username', htmlbody.username);
          redirect = response.redirect;
        }
        else {
          alert(response.message);
        }
      }
    }

    request.send("username=" + encodeURIComponent(htmlbody.username) + "&password=" + encodeURIComponent(htmlbody.password));
    if (redirect.includes("admin")) {
      sessionStorage.setItem("username", htmlbody.username);
      this.router.navigate(['/admin']);
    }
    else {
      sessionStorage.setItem("username", htmlbody.username);
      this.router.navigate(['/client']);
    }
  }
}
