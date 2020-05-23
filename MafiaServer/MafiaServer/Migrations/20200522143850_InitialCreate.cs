using System;
using Microsoft.EntityFrameworkCore.Migrations;

namespace MafiaServer.Migrations
{
    public partial class InitialCreate : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "GameCreator",
                columns: table => new
                {
                    ID = table.Column<Guid>(nullable: false),
                    PlayerId = table.Column<Guid>(nullable: false),
                    RoomId = table.Column<Guid>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_GameCreator", x => x.ID);
                });

            migrationBuilder.CreateTable(
                name: "GameSession",
                columns: table => new
                {
                    GameSessionId = table.Column<Guid>(nullable: false),
                    GameTime = table.Column<TimeSpan>(nullable: false),
                    VoteTime = table.Column<TimeSpan>(nullable: false),
                    RoomId = table.Column<Guid>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_GameSession", x => x.GameSessionId);
                });

            migrationBuilder.CreateTable(
                name: "Room",
                columns: table => new
                {
                    RoomId = table.Column<Guid>(nullable: false),
                    Name = table.Column<string>(nullable: true),
                    MafiaAmount = table.Column<int>(nullable: false),
                    CivilAmount = table.Column<int>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Room", x => x.RoomId);
                });

            migrationBuilder.CreateTable(
                name: "Vote",
                columns: table => new
                {
                    VoteId = table.Column<Guid>(nullable: false),
                    VotingPlayerId = table.Column<Guid>(nullable: false),
                    VotedPlayerId = table.Column<Guid>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Vote", x => x.VoteId);
                });

            migrationBuilder.CreateTable(
                name: "Player",
                columns: table => new
                {
                    PlayerId = table.Column<Guid>(nullable: false),
                    Role = table.Column<string>(nullable: true),
                    Name = table.Column<string>(nullable: true),
                    IsAlive = table.Column<bool>(nullable: false),
                    HasVoted = table.Column<bool>(nullable: false),
                    RoomId = table.Column<Guid>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Player", x => x.PlayerId);
                    table.ForeignKey(
                        name: "FK_Player_Room_RoomId",
                        column: x => x.RoomId,
                        principalTable: "Room",
                        principalColumn: "RoomId",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_Player_RoomId",
                table: "Player",
                column: "RoomId");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "GameCreator");

            migrationBuilder.DropTable(
                name: "GameSession");

            migrationBuilder.DropTable(
                name: "Player");

            migrationBuilder.DropTable(
                name: "Vote");

            migrationBuilder.DropTable(
                name: "Room");
        }
    }
}
