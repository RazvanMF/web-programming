using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using ASPNET_Template.Models;

namespace ASPNET_Template.Data
{
    public class WebExamContext : DbContext
    {
        public WebExamContext (DbContextOptions<WebExamContext> options)
            : base(options)
        {
        }

        // public DbSet<ASPNET_Template.Models.Model1> Model1 { get; set; } = default!;
    }
}
