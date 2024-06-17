﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using Lab10WebAssignment.Data;
using Lab10WebAssignment.Models;

namespace Lab10WebAssignment.Controllers
{
    public class BooksController : Controller
    {
        private readonly WebDatabaseContext _context;
        static public List<Log> logs = new List<Log>();

        public BooksController(WebDatabaseContext context)
        {
            _context = context;
        }

        private bool ValidateSession()
        {
            int? id = HttpContext.Session.GetInt32("UserID");
            return id != null;
        }

        // GET: Books
        public async Task<IActionResult> Index(string searchTitle, string searchAuthor, string searchGenre)
        {
            if (!ValidateSession())
                return RedirectToAction("Error", "Login");

            System.Diagnostics.Debug.WriteLine(searchAuthor + " " + searchGenre);
            var books = from book in _context.Book select book;
            if (!String.IsNullOrEmpty(searchTitle))
                books = books.Where(entry => entry.Title.ToLower().Contains(searchTitle.ToLower()));
            if (!String.IsNullOrEmpty(searchAuthor))
                books = books.Where(entry => entry.Author.ToLower().Contains(searchAuthor.ToLower()));
            if (!String.IsNullOrEmpty(searchGenre))
                books = books.Where(entry => entry.Genre.ToLower().Contains(searchGenre.ToLower()));

            if (!(searchAuthor == null && searchGenre == null && searchTitle == null))
                logs.Add(new Log { TitleFilter = searchTitle, AuthorFilter = searchAuthor, GenreFilter = searchGenre });
            return View(await books.ToListAsync());
        }

        public async Task<IActionResult> FilterLog()
        {
            if (!ValidateSession())
                return RedirectToAction("Error", "Login");

            return View(logs);
        }

        // GET: Books/Details/5
        public async Task<IActionResult> Details(int? id)
        {
            if (!ValidateSession())
                return RedirectToAction("Error", "Login");

            if (id == null)
            {
                return NotFound();
            }

            var book = await _context.Book
                .FirstOrDefaultAsync(m => m.Id == id);
            if (book == null)
            {
                return NotFound();
            }

            return View(book);
        }

        // GET: Books/Create
        public IActionResult Create()
        {
            if (!ValidateSession())
                return RedirectToAction("Error", "Login");

            return View();
        }

        // POST: Books/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("Id,Title,Author,Pages,Genre")] Book book)
        {
            if (!ValidateSession())
                return RedirectToAction("Error", "Login");

            if (ModelState.IsValid)
            {
                _context.Add(book);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            return View(book);
        }

        // GET: Books/Edit/5
        public async Task<IActionResult> Edit(int? id)
        {
            if (!ValidateSession())
                return RedirectToAction("Error", "Login");

            if (id == null)
            {
                return NotFound();
            }

            var book = await _context.Book.FindAsync(id);
            if (book == null)
            {
                return NotFound();
            }
            return View(book);
        }

        // POST: Books/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, [Bind("Id,Title,Author,Pages,Genre")] Book book)
        {
            if (!ValidateSession())
                return RedirectToAction("Error", "Login");

            if (id != book.Id)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(book);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!BookExists(book.Id))
                    {
                        return NotFound();
                    }
                    else
                    {
                        throw;
                    }
                }
                return RedirectToAction(nameof(Index));
            }
            return View(book);
        }

        // GET: Books/Delete/5
        public async Task<IActionResult> Delete(int? id)
        {
            if (!ValidateSession())
                return RedirectToAction("Error", "Login");

            if (id == null)
            {
                return NotFound();
            }

            var book = await _context.Book
                .FirstOrDefaultAsync(m => m.Id == id);
            if (book == null)
            {
                return NotFound();
            }

            return View(book);
        }

        // POST: Books/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            if (!ValidateSession())
                return RedirectToAction("Error", "Login");

            var book = await _context.Book.FindAsync(id);
            if (book != null)
            {
                _context.Book.Remove(book);
            }

            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }

        private bool BookExists(int id)
        {
            return _context.Book.Any(e => e.Id == id);
        }
    }
}
