using System.ComponentModel.DataAnnotations;

namespace Lab10WebAssignment.Models
{
	public class Book
	{
		public int Id { get; set; }
		[Required][MaxLength(128)] public string Title { get; set; }
        [Required][MaxLength(128)] public string Author { get; set; }
        [Required][Range(1, int.MaxValue)] public int Pages { get; set; }
        [Required][MaxLength(128)] public string Genre { get; set; }
	}
}
