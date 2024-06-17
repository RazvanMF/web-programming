using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace SDP.Models
{
	public class Project
	{
		[Key][DatabaseGenerated(DatabaseGeneratedOption.Identity)]
		public int ProjectId { get; set; }
		[ForeignKey("SoftwareDeveloper")]
		public int SoftwareDeveloperId { get; set; }
		public SoftwareDeveloper SoftwareDeveloper { get; set; }
		public string Name { get; set; } = string.Empty;
		public string Description { get; set; } = string.Empty;
		public string Members { get; set; } = string.Empty;
	}
}
