import internal from "stream";

export class Book{
  _id: number;
  _author: string;
  _title: string;
  _pages: number;
  _genre: string;
  _lended_to: string;

  constructor(id: number, author: string, title: string, pages: number, genre: string, lended_to: string) {
    this._id = id;
    this._author = author;
    this._title = title;
    this._pages = pages;
    this._genre = genre;
    this._lended_to = lended_to;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get author(): string {
    return this._author;
  }

  set author(value: string) {
    this._author = value;
  }

  get title(): string {
    return this._title;
  }

  set title(value: string) {
    this._title = value;
  }

  get pages(): number {
    return this._pages;
  }

  set pages(value: number) {
    this._pages = value;
  }

  get genre(): string {
    return this._genre;
  }

  set genre(value: string) {
    this._genre = value;
  }

  get lended_to(): string {
    return this._lended_to;
  }

  set lender(value: string) {
    this._lended_to = value;
  }
}
