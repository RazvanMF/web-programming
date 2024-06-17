import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import {NgForOf} from "@angular/common";
import {Book} from "../../Shared/Book";
import axios from "axios";
import {IDString} from "../../Shared/IDString";


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
  standalone: true,
  imports: [FormsModule, NgForOf],
})
export class AdminComponent {
  addauthor: string = '';
  addtitle: string = '';
  addpages: number = 0;
  addgenre: string = '';
  updateid: number = 0;
  updateauthor: string = '';
  updatetitle: string = '';
  updatepages: number = 0;
  updategenre: string = '';

  items: Book[] = [];
  addlender: IDString[] = [];
  updatelender: IDString[] = [];
  addlendername: string = "null";
  updatelendername: string = "null";

  constructor(private router: Router) {}

  add() {
    const htmlbody = {
      author: this.addauthor,
      title: this.addtitle,
      pages: this.addpages,
      genre: this.addgenre,
      lended_to: this.addlendername
    };

    let request = new XMLHttpRequest();
    request.open("POST", "http://localhost/lab06-web/addbook.php", false);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = function () {
      if (request.readyState === 4 && request.status === 200) {
        let response = JSON.parse(request.responseText)
        if (response.success) {
          alert(response.message);
        }
        else {
          alert(response.message);
        }
      }
    }

    request.send("author=" + encodeURIComponent(htmlbody.author) +
      "&title=" + encodeURIComponent(htmlbody.title) +
      "&pages=" + encodeURIComponent(htmlbody.pages) +
      "&genre=" + encodeURIComponent(htmlbody.genre) +
      "&lended_to=" + encodeURIComponent(htmlbody.lended_to));

    this.items = [];
    axios.get("http://localhost/lab06-web/readbooks.php")
      .then(response => {
        response.data.forEach((entry: { id: number; author: string; title: string; pages: number; genre: string; lended_to: string; }) => this.items.push(new Book(entry.id, entry.author, entry.title, entry.pages, entry.genre, entry.lended_to)));
      });
  }

  update() {
    const htmlbody = {
      id: this.updateid,
      author: this.updateauthor,
      title: this.updatetitle,
      pages: this.updatepages,
      genre: this.updategenre,
      lended_to: this.updatelendername
    };

    let request = new XMLHttpRequest();
    request.open("POST", "http://localhost/lab06-web/updatebook.php", false);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.onreadystatechange = function () {
      if (request.readyState === 4 && request.status === 200) {
        let response = JSON.parse(request.responseText)
        if (response.success) {
          alert(response.message);
        }
        else {
          alert(response.message);
        }
      }
    }

    request.send("id=" + encodeURIComponent(htmlbody.id) +
      "&author=" + encodeURIComponent(htmlbody.author) +
      "&title=" + encodeURIComponent(htmlbody.title) +
      "&pages=" + encodeURIComponent(htmlbody.pages) +
      "&genre=" + encodeURIComponent(htmlbody.genre) +
      "&lended_to=" + encodeURIComponent(htmlbody.lended_to));

    this.items = [];
    axios.get("http://localhost/lab06-web/readbooks.php")
      .then(response => {
        response.data.forEach((entry: { id: number; author: string; title: string; pages: number; genre: string; lended_to: string; }) => this.items.push(new Book(entry.id, entry.author, entry.title, entry.pages, entry.genre, entry.lended_to)));
      });
  }

  ngOnInit(): void {
    if (localStorage.getItem("username") !== "admin")
      this.router.navigate(['/']);


    axios.get("http://localhost/lab06-web/readbooks.php")
      .then(response => {
        response.data.forEach((entry: { id: number; author: string; title: string; pages: number; genre: string; lended_to: string; }) => this.items.push(new Book(entry.id, entry.author, entry.title, entry.pages, entry.genre, entry.lended_to)));
      });

    this.addlender.push(new IDString(0, "null"));
    this.updatelender.push(new IDString(0, "null"));
    axios.get("http://localhost/lab06-web/gatherusers.php")
      .then(response => {
        let index = 1;
        response.data.users.forEach((entry: string) => {
          this.addlender.push(new IDString(index, entry));
          this.updatelender.push(new IDString(index, entry));
          index += 1;
        })
      });
  }

  selectorAdd(event: Object) {
    // @ts-ignore
    console.log(event.target.value);
    // @ts-ignore
    this.addlendername = event.target.value;
  }

  selectorUpdate(event: Object) {
    // @ts-ignore
    console.log(event.target.value);
    // @ts-ignore
    this.updatelendername = event.target.value;
  }

  onClickTweak(_id: number) {
    this.updateid = _id;
  }

  onClickDelete(_id: number) {
    if (confirm("are you sure you want to delete this? >_<\ndeleting is kinda permanent :P")) {
      let request = new XMLHttpRequest();
      request.open("POST", "http://localhost/lab06-web/deletebook.php", false);
      request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
      request.onreadystatechange = function () {
        if (request.readyState === 4 && request.status === 200) {
          let response = JSON.parse(request.responseText)
          if (response.success) {
            alert(response.message);
          }
          else {
            alert(response.message);
          }
        }
      }

      request.send("id=" + encodeURIComponent(_id));

      this.items = [];
      axios.get("http://localhost/lab06-web/readbooks.php")
        .then(response => {
          response.data.forEach((entry: { id: number; author: string; title: string; pages: number; genre: string; lended_to: string; }) => this.items.push(new Book(entry.id, entry.author, entry.title, entry.pages, entry.genre, entry.lended_to)));
        });
    }
  }

  goBack() {
    sessionStorage.removeItem("username");
    this.router.navigate(['/']);
  }

  purgeFromUpdate() {
    this.updateid = -1;
  }
}
