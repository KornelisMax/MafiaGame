using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace MafiaServer.Models
{
    [Table("GameSession")]
    public class GameSession
    {
        [Key]
        public Guid ID { get; set; }
        public int MyProperty { get; set; }
        [ForeignKey("Player")]
        public List<Player> Players { get; set; }
        public TimeSpan GameTime { get; set; }
        public TimeSpan VoteTime { get; set; }
        [ForeignKey("Room")]
        public Guid RoomId { get; set; }
    }
}
