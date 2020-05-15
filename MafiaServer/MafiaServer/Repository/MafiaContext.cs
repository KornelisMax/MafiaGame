using MafiaServer.Models;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace MafiaServer.Repository
{
    public class MafiaContext : DbContext
    {
        public MafiaContext(DbContextOptions<MafiaContext> options)
            : base(options)
        {
        }

        public DbSet<Player> Players { get; set; }
        public DbSet<GameSession> GameSessions { get; set; }
        public DbSet<Room> Rooms{ get; set; }
        public DbSet<Vote> Votes{ get; set; }
        public DbSet<GameCreator> GameCreators{ get; set; }
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlServer(@"Data Source=(localdb)\ProjectsV13;Initial Catalog=MafiaDB;");
        }

    }
}
