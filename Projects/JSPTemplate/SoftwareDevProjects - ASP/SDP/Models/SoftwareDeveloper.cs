using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace SDP.Models
{
	public class SoftwareDeveloper
	{
		[Key][DatabaseGenerated(DatabaseGeneratedOption.Identity)]
		public int SoftwareDeveloperId { get; set;}

		public string Name { get; set;} = string.Empty;
		public int Age { get; set;}
		public string Skills { get; set; } = string.Empty;
	}
}
