using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace MafiaServer.Models
{
    [Table("Player")]
    public class Player
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public Guid PlayerId { get; set; }
        public string Role { get; set; }
        public string Name { get; set; }
        public bool IsAlive { get; set; }
        public bool HasVoted { get; set; }

        [ForeignKey("Room")]
        public Guid RoomId { get; set; }
        public Room Room { get; set; }
    }

}
