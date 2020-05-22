using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace MafiaServer.Models
{
    [Table("Room")]
    public class Room
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public Guid RoomId { get; set; }
        public string Name { get; set; }
        public int MafiaAmount { get; set; }
        public int CivilAmount { get; set; }


        public virtual ICollection<Player> Players { get; set; }
    }
}
