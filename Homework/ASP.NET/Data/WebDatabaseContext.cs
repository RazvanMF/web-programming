using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using Lab10WebAssignment.Models;

namespace Lab10WebAssignment.Data
{
    public class WebDatabaseContext : DbContext
    {
        public WebDatabaseContext (DbContextOptions<WebDatabaseContext> options)
            : base(options)
        {
        }

        public DbSet<Lab10WebAssignment.Models.Book> Book { get; set; } = default!;
        public DbSet<Lab10WebAssignment.Models.User> User { get; set; } = default!;
    }
}
