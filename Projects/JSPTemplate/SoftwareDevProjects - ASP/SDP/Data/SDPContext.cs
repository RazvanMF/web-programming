using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using SDP.Models;

namespace SDP.Data
{
    public class SDPContext : DbContext
    {
        public SDPContext (DbContextOptions<SDPContext> options)
            : base(options)
        {
        }

        public DbSet<SDP.Models.Project> Project { get; set; } = default!;
        public DbSet<SDP.Models.SoftwareDeveloper> SoftwareDeveloper { get; set; } = default!;
    }
}
