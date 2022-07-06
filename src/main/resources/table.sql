drop table if exists `article`;

create table article
(
	`id` int auto_increment,
    `cid` int not null comment '文章所属分类id',
    `banner` varchar(100) not null comment '配图',
    `isTop` bool default false comment '是否置顶',
    `pubTime` datetime not null comment '发布日期',
    `title` varchar(100) not null,
    `summary` varchar(255) not null,
    `content` text not null,
    `viewsCount` int default 0,
    `commentsCount` int default 0,
    primary key(`id`)
);

drop table if exists `category`;

create table category
(
	`id` int auto_increment,
    `name` varchar(50) not null,
    primary key(`id`)
);

drop table if exists `friend`;

-- 友链
create table friend
(
	`id` int auto_increment,
    `siteName` varchar(100) not null,
    `path` varchar(100) not null,
    `desc` varchar(255) not null,
    primary key(`id`)
);

drop table if exists `comment`;

create table `comment`
(
	`id` int auto_increment,
    `postId` int not null comment '评论所属文章id',
    `parentId` int default null comment '父级评论id',
    `fromUserId` int default null,
    `toUserId` int default null,
    `content` text not null,
    `createTime` datetime not null,
    primary key(`id`)
);

drop table if exists `user`;

-- 用户表
create table `user`
(
	`id` int auto_increment,
    `username` varchar(50) not null,
    `password` varchar(255) default null,
    `email` varchar(50) default null,
    `avatar` blob not null,
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key(`id`)
);

drop table if exists `role`;

-- 角色表
create table `role`
(
	`id` int auto_increment,
    `name` varchar(100) default null,
    `role_key` varchar(100) default null comment '角色权限字符串',
    `status` char(1) default '0' comment '角色状态(0正常，1停用)',
    `del_flag` int(1) default 0 comment '是否删除(0未删除 1已删除)',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key(`id`)
);

drop table if exists `permission`;

create table `permission`
(
	`id` int auto_increment,
    `perm_name` varchar(100) not null comment '权限名称',
    `path` varchar(200) default null comment '路由地址',
    `status` char(1) default '0' comment '权限状态(0正常，1停用)',
    `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
    `del_flag` int(11) DEFAULT '0' COMMENT '是否删除(0未删除 1已删除)',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key(`id`)
);

drop table if exists `user_role`;

create table `user_role`
(
	`user_id` int not null,
    `role_id` int not null,
    primary key(`user_id`, `role_id`)
);

drop table if exists `role_perm`;

create table `role_perm`
(
	`role_id` int not null,
    `perm_id` int not null,
    primary key(`role_id`, `perm_id`)
);
