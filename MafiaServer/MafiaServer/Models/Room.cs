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
        public Guid ID { get; set; }
        [Column("Name")]
        public string Name { get; set; }

        [ForeignKey("Player")]
        public ICollection<Player> Players { get; set; }
        [Column("GameCreatorId")]
        [ForeignKey("GameCreator")]
        public Guid GameCreatorId { get; set; }
        [ForeignKey("GameSession")]
        public Guid GameSessionId { get; set; }
        public int MafiaAmount { get; set; }
        public int CivilAmount { get; set; }
    }
}
