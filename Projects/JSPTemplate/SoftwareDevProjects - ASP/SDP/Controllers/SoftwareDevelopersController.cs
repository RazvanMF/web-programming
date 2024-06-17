using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using SDP.Data;
using SDP.Models;

namespace SDP.Controllers
{
    public class SoftwareDevelopersController : Controller
    {
        private readonly SDPContext _context;

        public SoftwareDevelopersController(SDPContext context)
        {
            _context = context;
        }

        // GET: SoftwareDevelopers
        public async Task<IActionResult> Index()
        {
            return View(await _context.SoftwareDeveloper.ToListAsync());
        }

        // GET: SoftwareDevelopers/Details/5
        public async Task<IActionResult> Details(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var softwareDeveloper = await _context.SoftwareDeveloper
                .FirstOrDefaultAsync(m => m.SoftwareDeveloperId == id);
            if (softwareDeveloper == null)
            {
                return NotFound();
            }

            return View(softwareDeveloper);
        }

        // GET: SoftwareDevelopers/Create
        public IActionResult Create()
        {
            return View();
        }

        // POST: SoftwareDevelopers/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("SoftwareDeveloperId,Name,Age,Skills")] SoftwareDeveloper softwareDeveloper)
        {
            if (ModelState.IsValid)
            {
                _context.Add(softwareDeveloper);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            return View(softwareDeveloper);
        }

        // GET: SoftwareDevelopers/Edit/5
        public async Task<IActionResult> Edit(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var softwareDeveloper = await _context.SoftwareDeveloper.FindAsync(id);
            if (softwareDeveloper == null)
            {
                return NotFound();
            }
            return View(softwareDeveloper);
        }

        // POST: SoftwareDevelopers/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, [Bind("SoftwareDeveloperId,Name,Age,Skills")] SoftwareDeveloper softwareDeveloper)
        {
            if (id != softwareDeveloper.SoftwareDeveloperId)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(softwareDeveloper);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!SoftwareDeveloperExists(softwareDeveloper.SoftwareDeveloperId))
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
            return View(softwareDeveloper);
        }

        // GET: SoftwareDevelopers/Delete/5
        public async Task<IActionResult> Delete(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var softwareDeveloper = await _context.SoftwareDeveloper
                .FirstOrDefaultAsync(m => m.SoftwareDeveloperId == id);
            if (softwareDeveloper == null)
            {
                return NotFound();
            }

            return View(softwareDeveloper);
        }

        // POST: SoftwareDevelopers/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            var softwareDeveloper = await _context.SoftwareDeveloper.FindAsync(id);
            if (softwareDeveloper != null)
            {
                _context.SoftwareDeveloper.Remove(softwareDeveloper);
            }

            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }

        private bool SoftwareDeveloperExists(int id)
        {
            return _context.SoftwareDeveloper.Any(e => e.SoftwareDeveloperId == id);
        }
    }
}
