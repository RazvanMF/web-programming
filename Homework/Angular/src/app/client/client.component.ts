import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import axios from "axios";
import {NgForOf} from "@angular/common";
import {Book} from "../../Shared/Book";
import $ from 'jquery';
import {IDString} from "../../Shared/IDString";
import {response} from "express";

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css'],
  standalone: true,
  imports: [FormsModule, NgForOf],
})
export class ClientComponent {
  items: Book[] = [];
  authors: IDString[] = [];
  genres: IDString[] = [];
  currentFilterAuthor: string = "All Authors";
  currentFilterGenre: string = "All Genres";

  filters: string[] = [];

  constructor(private router: Router) {}

  ngOnInit(): void {
    if (sessionStorage.getItem("username") == null)
      this.router.navigate(['/'])

    axios.get("http://localhost:80/lab06-web/client_getauthors.php")
      .then(response => {
        let index = 1;
        this.authors.push(new IDString(0, "All Authors"));
        response.data.authors.forEach((entry: string) => {
          this.authors.push(new IDString(index, entry));
          index += 1;
        })
      });

    axios.get("http://localhost:80/lab06-web/client_getgenres.php")
      .then(response => {
        let index = 1;
        this.genres.push(new IDString(0, "All Genres"));
        response.data.genres.forEach((entry: string) => {
          this.genres.push(new IDString(index, entry));
          index += 1;
        })
      });

    this.populateTable();
  }

  populateTable() {
    this.items = [];
    axios.get("http://localhost:80/lab06-web/readbooks.php")
      .then(response => {
        response.data.forEach((entry: { id: number; author: string; title: string; pages: number; genre: string; lended_to: string; }) => this.items.push(new Book(entry.id, entry.author, entry.title, entry.pages, entry.genre, entry.lended_to)));

        if (this.currentFilterAuthor !== "All Authors") {
          this.items = this.items.filter(entry => entry.author.trim() === this.currentFilterAuthor);
        }

        if (this.currentFilterGenre !== "All Genres") {
          this.items = this.items.filter(entry => entry.genre.trim() === this.currentFilterGenre);
        }

        let username = localStorage.getItem("username");
        this.items = this.items.filter(entry => entry.lended_to === username || entry.lended_to == null);
      });
  }

  onClickLend(_id: number, _username: string) {
    const htmlbody = {
      id: _id,
      username: (_username == null ? "null" : _username)
    };

    let request = new XMLHttpRequest();
    request.open("POST", "http://localhost/lab06-web/lenderfunctionality.php?username=" + _username + '&id=' + _id + '&caller=' + sessionStorage.getItem("username"), false);
    request.setRequestHeader("Content-Type", "application/json");
    request.onreadystatechange = function () {
      console.log(request.response);
      if (request.readyState === 4 && request.status === 200) {
        console.log("LOL WHY DOES IT NOT WORK THEN??????");
      }
    }

    request.send();

    this.populateTable();
  }

  selectorAuthor($event: MouseEvent) {
    // @ts-ignore
    this.currentFilterAuthor = $event.target.value;
    this.populateTable();
    this.filters.push(`${this.currentFilterAuthor} + ${this.currentFilterGenre}`);
  }

  selectorGenre($event: MouseEvent) {
    // @ts-ignore
    this.currentFilterGenre = $event.target.value;
    this.populateTable();
    this.filters.push(`${this.currentFilterAuthor} + ${this.currentFilterGenre}`);
  }

  goBack() {
    sessionStorage.removeItem("username");
    this.router.navigate(['/']);
  }
}
