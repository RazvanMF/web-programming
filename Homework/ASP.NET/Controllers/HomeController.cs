using Lab10WebAssignment.Models;
using Microsoft.AspNetCore.Mvc;
using System.Diagnostics;

namespace Lab10WebAssignment.Controllers
{
	public class HomeController : Controller
	{
		private readonly ILogger<HomeController> _logger;

		public HomeController(ILogger<HomeController> logger)
		{
			_logger = logger;
		}

		public IActionResult Index()
		{
            HttpContext.Session.Clear();
            return View();
		}

		public IActionResult Privacy()
		{
            HttpContext.Session.Clear();
            return View();
		}
	}
}
