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
    public class ProjectsController : Controller
    {
        private readonly SDPContext _context;
    

        public ProjectsController(SDPContext context)
        {
            _context = context;
            /*HttpContext.Session.SetString("ids", "");*/
        }

        // GET: Projects
        public async Task<IActionResult> Index(string username)
        {

            /*HttpContext.Session.SetString("username", username);*/
            var sDPContext = _context.Project.Include(p => p.SoftwareDeveloper);
            var projects = sDPContext.Where(project => project.Members.ToLower().Contains(username.ToLower()));
            return View(await projects.ToListAsync());
        }
        // GET: Projects
        public async Task<IActionResult> All()
        {
            var sDPContext = _context.Project.Include(p => p.SoftwareDeveloper);
            return View(await sDPContext.ToListAsync());
        }

        // GET: Projects/Details/5
        public async Task<IActionResult> Details(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var project = await _context.Project
                .Include(p => p.SoftwareDeveloper)
                .FirstOrDefaultAsync(m => m.ProjectId == id);
            if (project == null)
            {
                return NotFound();
            }

            return View(project);
        }

        // GET: Projects/Create
        public IActionResult Create()
        {
            ViewData["SoftwareDeveloperId"] = new SelectList(_context.Set<SoftwareDeveloper>(), "SoftwareDeveloperId", "SoftwareDeveloperId");
            return View();
        }

        // POST: Projects/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("ProjectId,SoftwareDeveloperId,Name,Description,Members")] Project project)
        {
            if (true)
            {
                _context.Add(project);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(All));
            }
            ViewData["SoftwareDeveloperId"] = new SelectList(_context.Set<SoftwareDeveloper>(), "SoftwareDeveloperId", "SoftwareDeveloperId", project.SoftwareDeveloperId);
            return View(project);
        }

        // GET: Projects/Edit/5
        public async Task<IActionResult> Edit(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var project = await _context.Project.FindAsync(id);
            if (project == null)
            {
                return NotFound();
            }
            ViewData["SoftwareDeveloperId"] = new SelectList(_context.Set<SoftwareDeveloper>(), "SoftwareDeveloperId", "SoftwareDeveloperId", project.SoftwareDeveloperId);
            return View(project);
        }

        // POST: Projects/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, [Bind("ProjectId,SoftwareDeveloperId,Name,Description,Members")] Project project)
        {
            if (id != project.ProjectId)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(project);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!ProjectExists(project.ProjectId))
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
            ViewData["SoftwareDeveloperId"] = new SelectList(_context.Set<SoftwareDeveloper>(), "SoftwareDeveloperId", "SoftwareDeveloperId", project.SoftwareDeveloperId);
            return View(project);
        }

        // GET: Projects/Delete/5
        public async Task<IActionResult> Delete(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var project = await _context.Project
                .Include(p => p.SoftwareDeveloper)
                .FirstOrDefaultAsync(m => m.ProjectId == id);
            if (project == null)
            {
                return NotFound();
            }

            return View(project);
        }

        // POST: Projects/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            var project = await _context.Project.FindAsync(id);
            if (project != null)
            {
                _context.Project.Remove(project);
            }

            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }

        private bool ProjectExists(int id)
        {
            return _context.Project.Any(e => e.ProjectId == id);
        }

        [HttpPost, ActionName("GetAll")]
        public IActionResult GetAll(string ids)
        {
            return RedirectToAction(nameof(All));
        }

        [HttpPost]
        public IActionResult UpdateSelectedProjects([FromBody] ProjectUpdateModel model)
        {
            // model.Developer and model.ProjectIds should now be correctly populated
            if (model != null && model.ProjectIds != null && !string.IsNullOrEmpty(model.Developer))
            {
                if(_context.SoftwareDeveloper.Any(dev => dev.Name == model.Developer))
                {
                    foreach(int id in model.ProjectIds)
                    {
                        Project p = _context.Project.First(pj => pj.ProjectId == id);
                        if (!p.Members.Contains(model.Developer))
                        {
                            p.Members += model.Developer + ";";        
                        }
                        _context.Project.Update(p);
                        _context.SaveChanges();
                    }
                }

                return Json(new { success = true, message = "Projects updated successfully." });
            }

            return Json(new { success = false, message = "Invalid data received." });
        }
    }

    public class ProjectUpdateModel
    {
        public string Developer { get; set; }
        public List<int> ProjectIds { get; set; }
    }
}


